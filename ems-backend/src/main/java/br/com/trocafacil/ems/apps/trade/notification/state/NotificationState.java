package br.com.trocafacil.ems.apps.trade.notification.state;

public interface NotificationState {

    NotificationState notificationState = null;
    public String notifyUser();
    public NotificationState setState(NotificationState state);


}
