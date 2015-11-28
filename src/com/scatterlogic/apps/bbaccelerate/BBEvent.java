package com.scatterlogic.apps.bbaccelerate;

public class BBEvent{
        private String eventTitle;
        private String eventContent;
        private String eventFurtherInfo;

        public BBEvent(String title, String content, String eventFurtherInfo){
            this.eventTitle = title;
            this.eventContent= content;
            this.eventFurtherInfo = eventFurtherInfo;
        }

        public String getTitle(){
            return eventTitle;
        }

        public String getContent(){
            return eventContent;
        }

        public String getEventFurtherInfo(){
            return eventFurtherInfo;
        }

}