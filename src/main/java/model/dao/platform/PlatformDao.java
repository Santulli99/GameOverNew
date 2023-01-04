package model.dao.platform;

import model.entity.Platform;

import java.util.ArrayList;

public interface PlatformDao <E extends Exception> {

    Platform searchPlatform(int id) throws E;
    ArrayList<Platform> searchAllPlatform() throws E;
    boolean createPlatform(Platform platform)throws E;
    //boolean updatePlatform(Platform model.dao.platform) throws E;

    Platform searchPlatformWithProduct(int id) throws E;

    //hash Map
    ArrayList<Platform>  searchAllPlatformWithProducts()throws E;


}
