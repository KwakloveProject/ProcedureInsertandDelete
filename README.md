자바를 이용해서 삽입이랑 삭제를 프로시저로 만들었습니다

create or replace PROCEDURE ADDDEPARTMENT( 
     D_NUM IN DEPARTMENT.D_NUM %TYPE,
     D_NAME IN DEPARTMENT.D_NAME %TYPE)
     IS
     BEGIN
     INSERT INTO DEPARTMENT(no,D_NUM,D_NAME) VALUES(department_seq.nextval,D_NUM,D_NAME);
      COMMIT;
    DBMS_OUTPUT.PUT_LINE(D_NAME||'학과 INSERT SUCCESS');
    END;

EXECUTE ADDDEPARTMENT('80','2');//연습 해볼려고 만들어보았습니다
commit;

    SELECT * FROM department;
    
    CREATE OR REPLACE PROCEDURE DELETE_DEPARTMENT(P_NO IN DEPARTMENT.NO%TYPE)
      IS
      BEGIN
         DELETE FROM DEPARTMENT
         WHERE P_NO = NO;
         COMMIT;
      END;


    EXECUTE DELETE_DEPARTMENT('67');//연습 해볼려고 만들어보았습니다
    commit; 


    create or replace TRIGGER DEPARTMENT_DELETE_TRG01//번외로 트리거도 만들어보았습니다. 데이터베이스에서 삭제할때 문구가 뜨게 만들었습니다.
 AFTER DELETE
 ON DEPARTMENT
 BEGIN
   DBMS_OUTPUT.PUT_LINE('삭제되었습니다.');
 END;
commit;
delete department where department.no=85;
