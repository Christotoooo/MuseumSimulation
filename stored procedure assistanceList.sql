CREATE OR REPLACE FUNCTION assistanceList(inputDate DATE)
	RETURNS text AS 
$BODY$
DECLARE 
	result TEXT DEFAULT '';
	disableAssi TEXT DEFAULT 'Assistance list for visitors with disabilities: ';
	babyAssi TEXT DEFAULT 'Assistance list for visitors with their babies: ';
	langAssi TEXT DEFAULT 'Assistance list for visitors with language barriers: ';

	rec_visitor   RECORD;
	cur_visitors CURSOR(inputDate DATE)
	FOR SELECT v.phone,name,notes,booknumber,activitytime 
	FROM visitor v JOIN
		(SELECT r.booknumber,phone,activitytime FROM BOOK b JOIN RESERVATION r ON b.booknumber=r.booknumber) T
	ON v.phone=T.phone
	WHERE DATE(activitytime)=inputDate;
BEGIN
	-- Open the cursor
	OPEN cur_visitors(inputDate);
	------------------loops
	LOOP
		-- fetch row into the film
		FETCH cur_visitors INTO rec_visitor;
		-- exit when no more row to fetch
		EXIT WHEN NOT FOUND;
		-- modify disabled
		IF rec_visitor.notes LIKE '%wheelchair%' 
			OR rec_visitor.notes LIKE '%wheel%'
			OR rec_visitor.notes LIKE '%chair%'
			OR rec_visitor.notes LIKE '%WHEEL%'
			OR rec_visitor.notes LIKE '%CHAIR%'
		THEN
			disableAssi := disableAssi ||' '||rec_visitor.name||','||rec_visitor.phone||'~' ;
		END IF;

		--modify baby
		IF rec_visitor.notes LIKE '%baby%' 
			OR rec_visitor.notes LIKE '%BABY%'
			OR rec_visitor.notes LIKE '%bab%'
			OR rec_visitor.notes LIKE '%kid%'
			OR rec_visitor.notes LIKE '%child%'
			OR rec_visitor.notes LIKE '%KID%'
			OR rec_visitor.notes LIKE '%CHILD%'
			OR rec_visitor.notes LIKE '%boy%'
			OR rec_visitor.notes LIKE '%BOY%'
			OR rec_visitor.notes LIKE '%GIRL%'
			OR rec_visitor.notes LIKE '%girl%'
		THEN
			babyAssi := babyAssi ||' '||rec_visitor.name||','||rec_visitor.phone||'*' ;
		END IF;

		--modify lang
		IF rec_visitor.notes LIKE '%tranlator%' 
			OR rec_visitor.notes LIKE '%translation%'
			OR rec_visitor.notes LIKE '%TRANS%'
			OR rec_visitor.notes LIKE '%lang%'
			OR rec_visitor.notes LIKE '%language%'
			OR rec_visitor.notes LIKE '%English%'
			OR rec_visitor.notes LIKE '%Chinese%'
			OR rec_visitor.notes LIKE '%German%'
			OR rec_visitor.notes LIKE '%French%'
			OR rec_visitor.notes LIKE '%country%'
			OR rec_visitor.notes LIKE '%Spanish%'
		THEN
			langAssi := langAssi ||' '||rec_visitor.name||','||rec_visitor.phone||'#' ;
		END IF;

	END LOOP;
	-------------------
	-- Close the cursor
	CLOSE cur_visitors;

	--result := disableAssi ||E'\n'|| babyAssi || E'\n' || langAssi;
	result := '<html>'||disableAssi ||'<br>'|| babyAssi || '<br>' || langAssi||'<html>';
	RETURN result;
END; 
$BODY$

LANGUAGE plpgsql;