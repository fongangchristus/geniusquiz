import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { QuizService } from 'app/entities/quiz/quiz.service';
import { IQuiz, Quiz } from 'app/shared/model/quiz.model';

describe('Service Tests', () => {
  describe('Quiz Service', () => {
    let injector: TestBed;
    let service: QuizService;
    let httpMock: HttpTestingController;
    let elemDefault: IQuiz;
    let expectedResult: IQuiz | IQuiz[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(QuizService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Quiz(0, 'AAAAAAA', 0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', currentDate, 0, currentDate, currentDate);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            duree: currentDate.format(DATE_TIME_FORMAT),
            datePublication: currentDate.format(DATE_TIME_FORMAT),
            dateExpiration: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Quiz', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            duree: currentDate.format(DATE_TIME_FORMAT),
            datePublication: currentDate.format(DATE_TIME_FORMAT),
            dateExpiration: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            duree: currentDate,
            datePublication: currentDate,
            dateExpiration: currentDate,
          },
          returnedFromService
        );

        service.create(new Quiz()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Quiz', () => {
        const returnedFromService = Object.assign(
          {
            type: 'BBBBBB',
            idMatiere: 1,
            entete: 'BBBBBB',
            description: 'BBBBBB',
            libele: 'BBBBBB',
            imageCouverture: 'BBBBBB',
            duree: currentDate.format(DATE_TIME_FORMAT),
            nbrQuestion: 1,
            datePublication: currentDate.format(DATE_TIME_FORMAT),
            dateExpiration: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            duree: currentDate,
            datePublication: currentDate,
            dateExpiration: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Quiz', () => {
        const returnedFromService = Object.assign(
          {
            type: 'BBBBBB',
            idMatiere: 1,
            entete: 'BBBBBB',
            description: 'BBBBBB',
            libele: 'BBBBBB',
            imageCouverture: 'BBBBBB',
            duree: currentDate.format(DATE_TIME_FORMAT),
            nbrQuestion: 1,
            datePublication: currentDate.format(DATE_TIME_FORMAT),
            dateExpiration: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            duree: currentDate,
            datePublication: currentDate,
            dateExpiration: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Quiz', () => {
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
