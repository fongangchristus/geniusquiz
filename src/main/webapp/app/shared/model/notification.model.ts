import { Moment } from 'moment';
import { TypeNotification } from 'app/shared/model/enumerations/type-notification.model';

export interface INotification {
  id?: number;
  titre?: string;
  sousTitre?: string;
  details?: string;
  dateNotif?: Moment;
  code?: string;
  typeNotification?: TypeNotification;
}

export class Notification implements INotification {
  constructor(
    public id?: number,
    public titre?: string,
    public sousTitre?: string,
    public details?: string,
    public dateNotif?: Moment,
    public code?: string,
    public typeNotification?: TypeNotification
  ) {}
}
