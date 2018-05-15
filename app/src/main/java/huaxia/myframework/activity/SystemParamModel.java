package huaxia.myframework.activity;


/**
 * Created by owen on 16/9/29.
 */
public class SystemParamModel {

    /**
     * playServer : http://lecloud.educdn.huan.tv/mediadns/ts/
     * mediaServer : http://192.168.80.112:8777/
     * rootId : 16
     */

    public String playServer;
    public String mediaServer;
    public String rootId;

    public String getPlayServer() {
        return playServer;
    }

    public void setPlayServer(String playServer) {
        this.playServer = playServer;
    }

    public String getMediaServer() {
        return mediaServer;
    }

    public void setMediaServer(String mediaServer) {
        this.mediaServer = mediaServer;
    }

    public String getRootId() {
        return rootId;
    }

    public void setRootId(String rootId) {
        this.rootId = rootId;
    }

    @Override
    public String toString() {
        return "SystemParamModel{" +
                "playServer='" + playServer + '\'' +
                ", mediaServer='" + mediaServer + '\'' +
                ", rootId='" + rootId + '\'' +
                '}';
    }
}
