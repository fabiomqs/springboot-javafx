package com.github.fabiomqs.stockui.fxml.controller;

import com.github.fabiomqs.stockui.client.WebClientStockClient;
import com.github.fabiomqs.stockui.domain.StockPrice;
import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.chart.XYChart.Data;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

import static java.lang.String.valueOf;
import static javafx.collections.FXCollections.observableArrayList;

@Component
public class ChartController implements Consumer<StockPrice> {

    @FXML
    private LineChart<String, Double> chart;
    private WebClientStockClient webClientStockClient;
    private ObservableList<Data<String, Double>> seriesData = observableArrayList();

    public ChartController(WebClientStockClient webClientStockClient) {
        this.webClientStockClient = webClientStockClient;
    }

    @FXML
    public void initialize() {
        String symbol = "SYMBOL";
        ObservableList<Series<String, Double>> data = observableArrayList();
        data.add(new Series<>(symbol, seriesData));
        chart.setData(data);

        webClientStockClient.pricesFor(symbol).subscribe(this);
    }

    @Override
    public void accept(StockPrice stockPrice) {
        // the accept method run on a different thred
        // listening to events from the back-end
        // so we add the Platform.runLater(() ->
        Platform.runLater(() ->
                seriesData.add(new Data<>(valueOf(stockPrice.getTime().getSecond()),
                        stockPrice.getPrice()))
        );
    }
}
