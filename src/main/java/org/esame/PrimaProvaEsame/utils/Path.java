package org.esame.PrimaProvaEsame.utils;


public class Path {

    public static class Web {
        public static final String BASE_INDEX = "/";
        public static final String INDEX = "/velocity/index/";
        public static final String LOGIN = "/velocity/login/";
        public static final String REGISTER = "/velocity/register/";
        public static final String LOGOUT = "/logout/";
        public static final String REMINDERS = "/reminders/";
        public static final String YOUR_REMINDERS = "/reminders/yours/";
        public static final String ALL_USER_REMINDERS = "/reminders/:author/";
        public static final String ADD_REMINDER = "/reminder/";
        public static final String REMOVE_REMINDER = "/reminder/:id/";
        public static final String FOLLOW_AUTHOR = "/follow/:author/";
        public static final String UNFOLLOW_AUTHOR = "/unfollow/:author/";
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

        public static String getALL_PUBLIC_REMINDERS() {
            return REMINDERS;
        }

        public static String getALL_YOUR_REMINDERS() {
            return YOUR_REMINDERS;
        }

        public static String getALL_USER_REMINDERS() {
            return ALL_USER_REMINDERS;
        }

        public static String getADD_REMINDER() {
            return ADD_REMINDER;
        }

        public static String getREMOVE_REMINDER() {
            return REMOVE_REMINDER;
        }


        public static String getSF_CONNECTION() {
            return SF_CONNECTION;
        }

        public static String getSF_LEADS() {
            return SF_LEADS;
        }
        public static String getREST_API_REMINDER() {
            return REST_SFPOST;
        }

        public static String getSF_DISCONNECTION() {
            return SF_DISCONNECTION;
        }

        public static String getFILTER_REMINDER() {
            return ADD_REMINDER;
        }


        public static String getREGISTER() {
            return REGISTER;
        }

        public static String getFOLLOW_AUTHOR() {
            return FOLLOW_AUTHOR;
        }
        public static String getUNFOLLOW_AUTHOR() {
            return UNFOLLOW_AUTHOR;
        }
    }

    public static class Template {
        public final static String INDEX = "/velocity/index/index.vm";
        public final static String LOGIN = "/velocity/login/login.vm";
        public final static String REGISTER = "/velocity/register/register.vm";
        public final static String REMINDERS_ALL = "/velocity/reminder/list_reminders.vm";
        public final static String REMINDER_NEW = "/velocity/reminder/add_reminder.vm";
        public static final String NOT_FOUND = "/velocity/notFound.vm";
    }

}
