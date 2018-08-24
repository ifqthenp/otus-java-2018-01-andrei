package com.otus.hw_15.messageSystem;

public abstract class Message {

    private final Address from;
    private final Address to;

    public Message(Address from, Address to) {
        this.from = from;
        this.to = to;
    }

    public Address getFrom() {
        return this.from;
    }

    public Address getTo() {
        return this.to;
    }

    public abstract void exec(Addressee addressee);
}
