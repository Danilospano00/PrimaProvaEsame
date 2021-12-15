package org.esame.utils;


public class Path {

    public static class Web {
        public static final String BASE_INDEX = "/";
        public static final String INDEX = "/index/";
        public static final String LOGIN = "/login/";
        public static final String REGISTER = "/register/";
        public static final String LOGOUT = "/logout/";
        public static final String MESSAGES = "/messages/";
        public static final String YOUR_MESSAGES = "/messages/yours/";
        public static final String ALL_USER_MESSAGES = "/messages/:author/";
        public static final String ADD_MESSAGES = "/message/";
        public static final String REMOVE_MESSAGE = "/message/:id/";
        public static final String SF_CONNECTION = "/sf/connection/";
        public static final String SF_DISCONNECTION = "/sf/disconnection/";
        public static final String SF_LEADS = "/sf/leads/";
        public static final String REST_SFPOST = "/api/post/";

        public static String getBaseIndex() {
            return BASE_INDEX;
        }
        public static String getINDEX() {
            return INDEX;
        }

        public static String getLOGIN() {
            return LOGIN;
        }

        public static String getLOGOUT() {
            return LOGOUT;
        }

        public static String getALL_PUBLIC_MESSAGES() {
            return MESSAGES;
        }

        public static String getALL_YOUR_MESSAGES() {
            return YOUR_MESSAGES;
        }

        public static String getALL_USER_MESSAGES() {
            return ALL_USER_MESSAGES;
        }

        public static String getADD_MESSAGE() {
            return ADD_MESSAGES;
        }

        public static String getREMOVE_MESSAGE() {
            return REMOVE_MESSAGE;
        }


        public static String getSF_CONNECTION() {
            return SF_CONNECTION;
        }

        public static String getSF_LEADS() {
            return SF_LEADS;
        }
        public static String getREST_API_MESSAGES() {
            return REST_SFPOST;
        }

        public static String getSF_DISCONNECTION() {
            return SF_DISCONNECTION;
        }



        public static String getREGISTER() {
            return REGISTER;
        }

       
    }

    public static class Template {
        public final static String INDEX = "/velocity/index/index.vm";
        public final static String LOGIN = "/velocity/login/login.vm";
        public final static String REGISTER = "/velocity/register/register.vm";
        public final static String MESSAGE_ALL = "/velocity/Message/lista_messaggi.vm";
        public final static String MESSAGE_NEW = "/velocity/Message/add_message.vm";
        public static final String NOT_FOUND = "/velocity/notFound.vm";
    }

}
