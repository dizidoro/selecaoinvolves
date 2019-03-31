package com.involves.selecao.gateway;

import com.involves.selecao.domain.Alert;
import com.involves.selecao.domain.AlertType;
import com.involves.selecao.domain.MonetaryAmount;
import com.involves.selecao.domain.Share;
import com.involves.selecao.gateway.mongo.MongoDbFactory;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.bson.codecs.BigDecimalCodec;
import org.bson.Document;
import org.bson.codecs.BigDecimalCodec;
import org.bson.types.Decimal128;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AlertMongoGateway implements AlertGateway {

    private static final String COLLECTION_NAME = "alerts";

    private static final String ALERT_TYPE = "alert_type";
    private static final String MARKETABLE_NAME = "marketable_name";
    private static final String POINT_OF_SALE = "point_of_sale";
    private static final String PRICE_MARGIN = "price_margin";
    private static final String SHARE_MARGIN = "share_margin";


    private final MongoDbFactory mongoFactory;

    @Autowired
    public AlertMongoGateway(MongoDbFactory mongoFactory) {
        this.mongoFactory = mongoFactory;
    }

    @Override
    public void save(List<Alert> alerts) {
        if (alerts.isEmpty()) {
            return;
        }
        List<Document> alertDocuments = this.toDocuments(alerts);
        this.alertCollection().insertMany(alertDocuments);
    }

    @Override
    public List<Alert> findAll() {
        FindIterable<Document> documents = this.alertCollection().find();
        List<Alert> alerts = new ArrayList<>();
        for (Document document : documents) {
            alerts.add(this.toDomain(document));
        }
        return alerts;
    }

    private MongoCollection<Document> alertCollection() {
        MongoDatabase database = this.mongoFactory.getDb();
        return database.getCollection(COLLECTION_NAME);
    }

    private List<Document> toDocuments(List<Alert> alerts) {
        return alerts.stream().map(this::toDocument).collect(Collectors.toList());
    }

    private Document toDocument(Alert alert) {
        Document document =
            new Document(ALERT_TYPE, alert.type().toString())
                .append(MARKETABLE_NAME, alert.marketableName())
                .append(POINT_OF_SALE, alert.pointOfSale());

        alert.shareMargin().ifPresent(shareMargin -> document.append(SHARE_MARGIN, shareMargin.value()));
        alert.priceMargin().ifPresent(priceMargin -> document.append(PRICE_MARGIN, priceMargin.value()));

        return document;
    }

    private Alert toDomain(Document document) {
        AlertType alertType = AlertType.valueOf(document.getString(ALERT_TYPE));
        String marketableName = document.getString(MARKETABLE_NAME);
        String pointOfSale = document.getString(POINT_OF_SALE);

        Integer shareMargin = document.getInteger(SHARE_MARGIN);
        Decimal128 priceMargin = document.get(PRICE_MARGIN, Decimal128.class);

        if (shareMargin != null) {
            return new Alert(alertType, marketableName, pointOfSale, new Share(shareMargin));
        } else if (priceMargin != null) {
            return new Alert(alertType, marketableName, pointOfSale, new MonetaryAmount(priceMargin.bigDecimalValue()));
        } else {
            return new Alert(alertType, marketableName, pointOfSale);
        }
    }

}
