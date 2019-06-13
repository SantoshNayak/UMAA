package umaa.uu.mca.model;

public class UploadImage {
    private String mName;
    private String mImageUrl;
    private String mDescription;

    public UploadImage(String name, String imageUrl, String description) {
        if(name.trim().equals("")){
            name="No Name";
        }
        mName = name;
        mImageUrl = imageUrl;
        mDescription = description;
    }

    public UploadImage(){}

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmImageUrl() {
        return mImageUrl;
    }

    public void setmImageUrl(String mImageUrl) {
        this.mImageUrl = mImageUrl;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }
}
