package io.github.haodongling.ppjokenew.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;


import java.io.Serializable;

import androidx.annotation.Nullable;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import io.github.haodongling.ppjokenew.BR;

public class User extends BaseObservable implements Parcelable {

    /**
     * id : 962
     * userId : 3223400206308231
     * name : 二师弟请随我来
     * avatar :
     * description :
     * likeCount : 0
     * topCommentCount : 0
     * followCount : 0
     * followerCount : 0
     * qqOpenId : null
     * expires_time : 0
     * score : 0
     * historyCount : 0
     * commentCount : 0
     * favoriteCount : 0
     * feedCount : 0
     * hasFollow : false
     */

    public int id;
    public long userId;
    public String name;
    public String avatar;
    public String description;
    public int likeCount;
    public int topCommentCount;
    public int followCount;
    public int followerCount;
    public String qqOpenId;
    public long expires_time;
    public int score;
    public int historyCount;
    public int commentCount;
    public int favoriteCount;
    public int feedCount;
    public boolean hasFollow;

    protected User(Parcel in) {
        id = in.readInt();
        userId = in.readLong();
        name = in.readString();
        avatar = in.readString();
        description = in.readString();
        likeCount = in.readInt();
        topCommentCount = in.readInt();
        followCount = in.readInt();
        followerCount = in.readInt();
        qqOpenId = in.readString();
        expires_time = in.readLong();
        score = in.readInt();
        historyCount = in.readInt();
        commentCount = in.readInt();
        favoriteCount = in.readInt();
        feedCount = in.readInt();
        hasFollow = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeLong(userId);
        dest.writeString(name);
        dest.writeString(avatar);
        dest.writeString(description);
        dest.writeInt(likeCount);
        dest.writeInt(topCommentCount);
        dest.writeInt(followCount);
        dest.writeInt(followerCount);
        dest.writeString(qqOpenId);
        dest.writeLong(expires_time);
        dest.writeInt(score);
        dest.writeInt(historyCount);
        dest.writeInt(commentCount);
        dest.writeInt(favoriteCount);
        dest.writeInt(feedCount);
        dest.writeByte((byte) (hasFollow ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj == null || !(obj instanceof User))
            return false;
        User newUser = (User) obj;
        return TextUtils.equals(name, newUser.name)
                && TextUtils.equals(avatar, newUser.avatar)
                && TextUtils.equals(description, newUser.description)
                && likeCount == newUser.likeCount
                && topCommentCount == newUser.topCommentCount
                && followCount == newUser.followCount
                && followerCount == newUser.followerCount
                && qqOpenId == newUser.qqOpenId
                && expires_time == newUser.expires_time
                && score == newUser.score
                && historyCount == newUser.historyCount
                && commentCount == newUser.commentCount
                && favoriteCount == newUser.favoriteCount
                && feedCount == newUser.feedCount
                && hasFollow == newUser.hasFollow;
    }

    @Bindable
    public boolean isHasFollow() {
        return hasFollow;
    }

    public void setHasFollow(boolean hasFollow) {
        this.hasFollow = hasFollow;
        notifyPropertyChanged(BR._all);
    }
}
