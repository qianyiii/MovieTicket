module com.qianyi.movieticket {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.qianyi.movieticket to javafx.fxml;
    exports com.qianyi.movieticket;
}