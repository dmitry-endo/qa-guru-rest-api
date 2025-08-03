package api.models;

import lombok.Data;

import java.util.List;

@Data
public class AccountBooksRequestModel {

    private String userId;
    private List<IsbnDataModel> collectionOfIsbns;

    @Data
    public static class IsbnDataModel {
        private String isbn;
    }
}
