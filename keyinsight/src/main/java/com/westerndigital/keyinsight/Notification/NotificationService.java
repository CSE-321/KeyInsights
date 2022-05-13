package com.westerndigital.keyinsight.Notification;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    public List<Notification> getAllNotificationSettings() {
        return notificationRepository.findAll();
    }

    public void updateNotificationSettings(Notification notification) {
        // queries for notification settings based on username and project name
        // can return null, so need to return as Optional object
        Optional<Notification> notificationFromDb = notificationRepository
            .findByJiraUsernameAndProject(notification.getJiraUsername(), 
                notification.getProjectName());

        // check if a notification was returned from the database
        if (notificationFromDb.isPresent()) {
            Notification n = notificationFromDb.get();

            // check if the notification to save is equal to the notification 
            // in the database
            if (notification.equals(n)) {
                // if so, don't need to update
                return;
            }

            // only update notification settings that needs updating
            compareAndUpdate(notification, n);

            notificationRepository.save(n);
            return;

        } else {
            Optional<Notification> notif = notificationRepository
                .findByJiraUsername(notification.getJiraUsername());

            if (notif.isPresent()) {
                Notification n = notif.get();

                // check if the notification to save is equal to the notification 
                // in the database
                if (notification.equals(n)) {
                    // if so, don't need to update
                    return;
                }

                n.setProjectName(notification.getProjectName());

                // only update notification settings that needs updating
                compareAndUpdate(notification, n);

                notificationRepository.save(n);
                return;

            } else {
                // handle exception for notification not found in database
                return;
            }
        }
    }

    // compare the original notification with the one from the database
    private void compareAndUpdate(Notification original, Notification fromDb) {
        if (!original.getSprintStatusSetting()
            .equals(fromDb.getSprintStatusSetting())) {

            fromDb.setSprintStatusSetting(original.getSprintStatusSetting());
        }

        if (!original.getTicketStatusSetting()
            .equals(fromDb.getTicketStatusSetting())) {

            fromDb.setTicketStatusSetting(original.getTicketStatusSetting());
        }

        if (!original.getUnfinishedTicketSetting()
            .equals(fromDb.getUnfinishedTicketSetting())) {

            fromDb.setUnfinishedTicketSetting(
                original.getUnfinishedTicketSetting());
        }

        if (!original.getProjectDigestReportSetting()
            .equals(fromDb.getProjectDigestReportSetting())) {

            fromDb.setProjectDigestReportSetting(
                original.getProjectDigestReportSetting());
        }

        if (!original.getWorkloadDigestReportSetting()
            .equals(fromDb.getWorkloadDigestReportSetting())) {

            fromDb.setWorkloadDigestReportSetting(
                original.getWorkloadDigestReportSetting());
        }
    }
}
