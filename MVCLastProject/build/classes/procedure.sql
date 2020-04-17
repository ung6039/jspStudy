DESC board;
SELECT * FROM board;
-- 게시판 목록 가지고 오기 
CREATE OR REPLACE PROCEDURE boardListData(
   pStart NUMBER,
   pEnd NUMBER,
   pResult OUT SYS_REFCURSOR
)
IS
BEGIN 
   OPEN pResult FOR
     SELECT no,subject,name,regdate,TO_CHAR(regdate,'YYYY-MM-DD') as dbday,hit,num
     FROM (SELECT no,subject,name,regdate,hit,rownum as num
     FROM (SELECT no,subject,name,regdate,hit 
     FROM board ORDER BY no DESC))
     WHERE num BETWEEN pStart AND pEnd;
END;
/
-- 총페이지 구하기 
CREATE OR REPLACE PROCEDURE boardTotalPage(
    pTotal OUT NUMBER
)
IS
BEGIN
   SELECT CEIL(COUNT(*)/10.0) INTO pTotal
   FROM board;
END;
/

-- 게시판 데이터 추가 
CREATE OR REPLACE PROCEDURE boardInsert(
   pName board.name%TYPE,
   pSubject board.subject%TYPE,
   pContent board.content%TYPE,
   pPwd board.pwd%TYPE
)
IS
  pMax NUMBER:=0;
BEGIN
  SELECT NVL(MAX(no)+1,1) INTO pMax
  FROM board;
  
  INSERT INTO board VALUES(
    pMax,pName,pSubject,pContent,pPwd,SYSDATE,0
  );
  
  COMMIT;
END;
/

-- 상세보기 
CREATE OR REPLACE PROCEDURE boardInfoData(
   pNo board.no%TYPE,
   pType NUMBER,
   pResult OUT SYS_REFCURSOR
)
IS
BEGIN
   -- 1 => 상세보기  , 2 => 수정하기 
   IF (pType=1) THEN
     UPDATE board SET
     hit=hit+1
     WHERE no=pNo;
     COMMIT;
   END IF;
   
   OPEN pResult FOR
     SELECT no,name,subject,content,regdate,hit
     FROM board
     WHERE no=pNo;
END;
/

-- 수정하기
CREATE OR REPLACE PROCEDURE boardUpdate(
   pNo board.no%TYPE,
   pName board.name%TYPE,
   pSubject board.subject%TYPE,
   pContent board.content%TYPE,
   pPwd board.pwd%TYPE,
   pResult OUT VARCHAR2
)
IS
   db_pwd board.pwd%TYPE;
BEGIN
   SELECT pwd INTO db_pwd
   FROM board
   WHERE no=pNo;
   
   IF (db_pwd=pPwd) THEN
     pResult:='true';
     UPDATE board SET
     name=pName,
     subject=pSubject,
     content=pContent
     WHERE no=pNo;
     COMMIT;
   ELSE
     pResult:='false';
   END IF;
END;
/

CREATE OR REPLACE PROCEDURE boardDelete(
   pNo board.no%TYPE,
   pPwd board.pwd%TYPE,
   pResult OUT VARCHAR2
)
IS
   db_pwd board.pwd%TYPE;
BEGIN
   SELECT pwd INTO db_pwd
   FROM board
   WHERE no=pNo;
   
   IF (pPwd=db_pwd) THEN
     pResult:='true';
     
     DELETE FROM board
     WHERE no=pNo;
     COMMIT;
   ELSE
     pResult:='false';
   END IF;
END;
/










