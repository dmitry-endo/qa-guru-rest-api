package api.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class AccountBooksRequestModel {

    private String userId;
    private List<IsbnDataModel> collectionOfIsbns;

    @Data
    @AllArgsConstructor
    public static class IsbnDataModel {
        private String isbn;
    }
}
