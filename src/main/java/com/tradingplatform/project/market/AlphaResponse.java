package com.tradingplatform.project.market;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AlphaResponse {

    @JsonProperty("Global Quote")
    private Quote globalQuote;

    public Quote getGlobalQuote() {
        return globalQuote;
    }

    public void setGlobalQuote(
            Quote globalQuote
    ) {
        this.globalQuote = globalQuote;
    }
}