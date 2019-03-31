angular.module('app.controllers', [])
    .controller('AlertListController', ['AlertService', function(AlertService) {

        var vm = this;
        vm.filter = filter;

        vm.types = {
            "PRICE_ABOVE_STIPULATED": {label: "Price above stipulated", badge: "badge-warning"},
            "PRICE_BELOW_STIPULATED": {label: "Price below stipulated", badge: "badge-success"},
            "SHARE_ABOVE_STIPULATED": {label: "Share above stipulated", badge: "badge-success"},
            "SHARE_BELOW_STIPULATED": {label: "Share below stipulated", badge: "badge-warning"},
            "RUPTURE": 	{label:"Rupture", badge: "badge-danger"}
        };

        vm.typesLabels = {
            "PRICE_ABOVE_STIPULATED": "Price above stipulated",
            "PRICE_BELOW_STIPULATED": "Price below stipulated",
            "SHARE_ABOVE_STIPULATED": "Share above stipulated",
            "SHARE_BELOW_STIPULATED": "Share below stipulated",
            "RUPTURE": 	"Rupture"
        };

        _init();

        function _init() {
            _getAllAlerts();
        };

        function _getAllAlerts() {
            AlertService.getAll().then(function (response) {
                vm.alerts = response;
                for (let i = 0; i < vm.alerts.length; i++) {
                    vm.alerts[i].style = vm.types[vm.alerts[i].type];
                }
                vm.alertsFiltered = vm.alerts;
                _createPointsOfSaleSet();
            });
        }

        function _createPointsOfSaleSet() {
            let set = new Set();
            for (let i = 0; i < vm.alerts.length; i++) {
                set.add(vm.alerts[i].pointOfSale);
            }
            vm.pointsOfSale = Array.from(set);
        }

        function filter() {
            vm.alertsFiltered = vm.alerts;

            if (vm.selectedType) {
                vm.alertsFiltered = vm.alertsFiltered.filter(alert => alert.type === vm.selectedType)
            }

            if (vm.selectedPointOfSale){
                vm.alertsFiltered = vm.alertsFiltered.filter(alert => alert.pointOfSale === vm.selectedPointOfSale);
            }
        }

    }]);