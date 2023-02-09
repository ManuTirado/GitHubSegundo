import { TestBed } from '@angular/core/testing';
import { PersonasserviceService } from './personasservice.service';

describe('PersonasserviceService', () => {
  let service: PersonasserviceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PersonasserviceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
