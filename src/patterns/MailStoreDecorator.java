package patterns;

import oop.MailStore;

abstract class MailStoreDecorator implements MailStore {
    protected MailStore mailStore;
    protected BodyEncriptionStrategy bodyStrategy;

    public MailStoreDecorator(MailStore ms){
        this.mailStore = ms;
    }
}
