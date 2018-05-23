package summer.app2;

import summer.BaseFramework.base.BaseModule;

public class SystemParamModel extends BaseModule {

    /**
     * playServer : http://lecloud.educdn.huan.tv/mediadns/ts/
     * mediaServer : http://192.168.80.112:8777/
     * rootId : 16
     */

    public int code;
    public Info info;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "SystemParamModel{" +
                "code=" + code +
                ", info=" + info +
                '}';
    }

    private class Info {
        public String playServer;
        public String mediaServer;
        public String rootId;

        @Override
        public String toString() {
            return "Info{" +
                    "playServer='" + playServer + '\'' +
                    ", mediaServer='" + mediaServer + '\'' +
                    ", rootId='" + rootId + '\'' +
                    '}';
        }

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
    }
}
