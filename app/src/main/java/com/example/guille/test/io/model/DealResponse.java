package com.example.guille.test.io.model;

import com.google.gson.annotations.SerializedName;

public class DealResponse {

        /*
        {
        "_id": "000000000000000000000000",
            "url": "sample string 1",
            "picture": "sample string 2",
            "description": "sample string 3",
            "originalPrice": 4,
            "discountedPrice": 5,
            "source": {
                "name": "sample string 1",
                        "url": "sample string 2"
            }
    }
    */

    @SerializedName(JsonKey.ID)
    String _id;

    @SerializedName(JsonKey.URL)
    String url;

    @SerializedName(JsonKey.PICTURE)
    String picture;

    @SerializedName(JsonKey.SOURCE)
    Source source;

    @SerializedName(JsonKey.DESCRIPTION)
    String description;

    @SerializedName(JsonKey.ORIGINAL_PRICE)
    String originalPrice;

    @SerializedName(JsonKey.DISCOUNTED_PRICE)
    String discountedPrice;

    public class Source {

        @SerializedName(JsonKey.NAME)
        String name;

        @SerializedName(JsonKey.URL_BASE)
        String url;

        public String getName() {
            return name;
        }

        public String getUrl() {
            return url;
        }
    }

//    GET and SET

    public String getDescription() {
        return description;
    }

    public String get_id() {
        return _id;
    }

    public String getUrl() {
        return url;
    }

    public String getPicture() {
        return picture;
    }

    public Source getSource() {
        return source;
    }

    public String getOriginalPrice() {
        return originalPrice;
    }

    public String getDiscountedPrice() {
        return discountedPrice;
    }
}
