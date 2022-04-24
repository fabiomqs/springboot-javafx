package com.github.fabiomqs.sample;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Attachment {

    private final StringProperty name = new SimpleStringProperty();
    private final BooleanProperty checked = new SimpleBooleanProperty();
    private String partId;
    private String attachmentId;

    public Attachment(String fileName, boolean checked, String partId, String attachmentId) {
        this.name.set(fileName);
        this.checked.set(checked);
        this.partId = partId;
        this.attachmentId = attachmentId;
    }

    public String getPartId() {
        return partId;
    }

    public String getAttachmentId() {
        return attachmentId;
    }

    public String getName() {
        return name.get();
    }

    public BooleanProperty checkedProperty() {
        return checked;
    }
}
