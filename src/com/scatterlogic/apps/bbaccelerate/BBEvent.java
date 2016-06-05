package com.scatterlogic.apps.bbaccelerate;

public class BBEvent{
        private String eventTitle;
        private String eventContent;

        public BBEvent(String title, String content){
            this.eventTitle = title;
            this.eventContent= content;
        }

        public String getTitle(){
            return eventTitle;
        }

        public String getContent(){
            return eventContent;
        }

}
