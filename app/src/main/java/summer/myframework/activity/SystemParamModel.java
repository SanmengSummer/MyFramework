package summer.myframework.activity;

import summer.myframework.network.retrofit.basenet.BaseModel;

public class SystemParamModel extends BaseModel {

    private Info info;

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "SystemParamModel{" +
                "info=" + info +
                '}';
    }

    public class Info {
        /**
         * playServer : http://lecloud.educdn.huan.tv/mediadns/ts/
         * mediaServer : http://192.168.80.112:8777/
         * rootId : 16
         */
        private String playServer;
        private String mediaServer;
        private String rootId;

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
