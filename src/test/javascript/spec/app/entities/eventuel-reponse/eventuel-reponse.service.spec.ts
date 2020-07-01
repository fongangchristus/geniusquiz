import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { EventuelReponseService } from 'app/entities/eventuel-reponse/eventuel-reponse.service';
import { IEventuelReponse, EventuelReponse } from 'app/shared/model/eventuel-reponse.model';

describe('Service Tests', () => {
  describe('EventuelReponse Service', () => {
    let injector: TestBed;
    let service: EventuelReponseService;
    let httpMock: HttpTestingController;
    let elemDefault: IEventuelReponse;
    let expectedResult: IEventuelReponse | IEventuelReponse[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(EventuelReponseService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new EventuelReponse(0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', false, 0);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a EventuelReponse', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new EventuelReponse()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a EventuelReponse', () => {
        const returnedFromService = Object.assign(
          {
            libele: 'BBBBBB',
            code: 'BBBBBB',
            imageCouverture: 'BBBBBB',
            description: 'BBBBBB',
            correctAnswer: true,
            point: 1,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of EventuelReponse', () => {
        const returnedFromService = Object.assign(
          {
            libele: 'BBBBBB',
            code: 'BBBBBB',
            imageCouverture: 'BBBBBB',
            description: 'BBBBBB',
            correctAnswer: true,
            point: 1,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a EventuelReponse', () => {
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
