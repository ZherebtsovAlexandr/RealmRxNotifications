package mansonheart.com.realmrxnotifications.model;

import io.realm.RealmModel;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Zherebtsov Alexandr on 07.01.2016.
 */

public class Category extends RealmObject {

    @PrimaryKey
    private int categoryId;
    private String name;

    public String getName() {
        return name;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

}

