package net;

import model.markets.Market;
import model.markets.OpenMarket;
import model.markets.OrderBook;

import java.net.http.HttpClient;
import java.util.List;

public class NovigHttpClient implements AutoCloseable {
    private final HttpClient client;
    private final String clientId;
    private final String clientSecret;
    private final NovigEnvironment environment;

    private NovigHttpClient(Builder b){
        clientId = b.clientId;
        clientSecret = b.clientSecret;
        environment = b.environment;
        client = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();
    }

    public Market getMarket(String marketId){
  //      Request r = Request.builder().method(HttpMethod.GET).path(environment.getRestUrl() + marketId).headers("accept", "application/json").build();
        return null;
    }

    public List<OpenMarket> getOpenMarkets(){
 //       Request.builder().method(HttpMethod.GET).path(request.path()).headers("accept", "application/json").build();
        return null;
    }

    public OrderBook getOrderBook(String marketId){
  //      Request.builder().method(HttpMethod.GET).path(request.path() + marketId).headers("accept", "application/json").build();
        return null;
    }

    @Override
    public void close() throws InterruptedException {
      client.close();
    }

    public String getClientId() {return clientId;}
    public String getClientSecret() {return clientSecret;}
    public NovigEnvironment getEnvironment() {return environment;}

    public static final class Builder {
        private String clientId;
        private String clientSecret;
        private NovigEnvironment environment;

        public Builder(){

        }

        public Builder clientId(String clientId){
            this.clientId = clientId;
            return this;
        }

        public Builder clientSecret(String clientSecret){
            this.clientSecret = clientSecret;
            return this;
        }

        public Builder environment(NovigEnvironment environment){
            this.environment = environment;
            return this;
        }

        public NovigHttpClient build(){
            if (clientId == null){
                throw new IllegalStateException("Client id must not be null");
            }

            if (clientSecret == null){
                throw new IllegalStateException("Client Secret must not be null");
            }

            if (environment == null){
                throw new IllegalStateException("Please Provide environment");
            }
            return new NovigHttpClient(this);
        }


    }



}
