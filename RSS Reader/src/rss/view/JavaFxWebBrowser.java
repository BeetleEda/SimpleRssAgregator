/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rss.view;


import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 * \author Евдокимова
 */
public class JavaFxWebBrowser extends JFXPanel {
    private WebView webView;
    private WebEngine webEngine;


    public JavaFxWebBrowser(String url) {
       Platform.setImplicitExit( false );
       Platform.runLater(() -> {
            initialiseJavaFXScene(url);
        });
    }

    private void initialiseJavaFXScene(String url) {
        webView = new WebView();
        webEngine = webView.getEngine();
        webEngine.load(url);

        Scene scene = new Scene(webView);
        setScene(scene);
    }
}
