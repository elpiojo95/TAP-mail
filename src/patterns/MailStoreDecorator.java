package patterns;

import oop.MailStore;

public abstract class MailStoreDecorator implements MailStore {
    protected MailStore mailStore;
    protected BodyEncriptionStrategy bodyStrategy;

    public MailStoreDecorator(MailStore ms){
        this.mailStore = ms;
    }
}
