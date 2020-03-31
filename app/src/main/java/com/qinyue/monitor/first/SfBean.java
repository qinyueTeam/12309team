package com.qinyue.monitor.first;

import java.io.Serializable;
import java.util.List;

/**
 * 创建人:qinyue
 * 创建日期:2020/3/31
 * 描述:
 **/
public class SfBean implements Serializable {

    /**
     * id : B0000
     * text : 共产党机关工作人员
     * state : closed
     * children : [{"id":"B0100","text":"政法委机关工作人员","state":"closed","children":[{"id":"B0101","text":"政法委书记","state":"open","children":[]},{"id":"B0102","text":"政法委副书记","state":"open","children":[]},{"id":"B0103","text":"政法委工作人员","state":"open","children":[]}]},{"id":"B0200","text":"纪委工作人员","state":"open","children":[]}]
     */

    private String id;
    private String text;
    private String state;
    private List<ChildrenBeanX> children;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<ChildrenBeanX> getChildren() {
        return children;
    }

    public void setChildren(List<ChildrenBeanX> children) {
        this.children = children;
    }

    public static class ChildrenBeanX implements Serializable{
        /**
         * id : B0100
         * text : 政法委机关工作人员
         * state : closed
         * children : [{"id":"B0101","text":"政法委书记","state":"open","children":[]},{"id":"B0102","text":"政法委副书记","state":"open","children":[]},{"id":"B0103","text":"政法委工作人员","state":"open","children":[]}]
         */

        private String id;
        private String text;
        private String state;
        private List<ChildrenBean> children;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public List<ChildrenBean> getChildren() {
            return children;
        }

        public void setChildren(List<ChildrenBean> children) {
            this.children = children;
        }

        public static class ChildrenBean implements Serializable{
            /**
             * id : B0101
             * text : 政法委书记
             * state : open
             * children : []
             */

            private String id;
            private String text;
            private String state;
            private List<?> children;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }

            public String getState() {
                return state;
            }

            public void setState(String state) {
                this.state = state;
            }

            public List<?> getChildren() {
                return children;
            }

            public void setChildren(List<?> children) {
                this.children = children;
            }
        }
    }
}
