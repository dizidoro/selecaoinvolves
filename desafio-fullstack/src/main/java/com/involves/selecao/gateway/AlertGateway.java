package com.involves.selecao.gateway;

import com.involves.selecao.domain.Alert;
import java.util.List;

public interface AlertGateway {

    void save(List<Alert> alerts);

    List<Alert> findAll();

}
