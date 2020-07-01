import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { ReponseUserService } from 'app/entities/reponse-user/reponse-user.service';
import { IReponseUser, ReponseUser } from 'app/shared/model/reponse-user.model';

describe('Service Tests', () => {
  describe('ReponseUser Service', () => {
    let injector: TestBed;
    let service: ReponseUserService;
    let httpMock: HttpTestingController;
    let elemDefault: IReponseUser;
    let expectedResult: IReponseUser | IReponseUser[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(ReponseUserService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new ReponseUser(0, currentDate, 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            dateReponse: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a ReponseUser', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            dateReponse: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateReponse: currentDate,
          },
          returnedFromService
        );

        service.create(new ReponseUser()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a ReponseUser', () => {
        const returnedFromService = Object.assign(
          {
            dateReponse: currentDate.format(DATE_TIME_FORMAT),
            libele: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateReponse: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of ReponseUser', () => {
        const returnedFromService = Object.assign(
          {
            dateReponse: currentDate.format(DATE_TIME_FORMAT),
            libele: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateReponse: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a ReponseUser', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
