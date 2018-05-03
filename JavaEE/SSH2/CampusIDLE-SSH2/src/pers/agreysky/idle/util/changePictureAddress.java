package pers.agreysky.idle.util;

public class changePictureAddress {
    private static String serverImagesPath = "http://xiaoyou.free.ngrok.cc/CampusIDLE-WEB/images/";
    public static String changeUserPictureAddress(String userPictureAddress) {
        String newUserPictureAddress = serverImagesPath + "usersPicture/"
                + userPictureAddress;
        return newUserPictureAddress;

    }
    public static String changeItemPictureAddress(String itemPictureAddress) {
        String newItemPictureAddress = serverImagesPath + "itemsPicture/"
                + itemPictureAddress;
        return newItemPictureAddress;

    }
    public static String changeFriendPictureAddress(
            String friendPictureAddress) {
        String newFriendPictureAddress = serverImagesPath + "friendsPicture/"
                + friendPictureAddress;
        return newFriendPictureAddress;

    }
}
