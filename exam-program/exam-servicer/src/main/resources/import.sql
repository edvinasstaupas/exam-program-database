-- noinspection SqlResolveForFile

insert into question (answer_a, answer_b, answer_c, question, answer_letter) values ('Teisingas', 'Neteisingas', 'Nes reik', 'Pasirinkite teisinga ats:', 'a');
insert into question (answer_a, answer_b, answer_c, question, answer_letter) values ('Test', 'TestTeisingas', 'Nes reik', 'Pasirinkite teisinga atsaaa:', 'b');
insert into question (answer_a, answer_b, answer_c, question, answer_letter) values ('TestBad', 'TestBad', 'Nes reikTeisingas', 'Pasirinkite teisingabbb ats:', 'c');

insert into questionnaire (type) values ('test');
insert into questionnaire (type) values ('test1');
insert into questionnaire (type) values ('test2');

insert into questionnaire_log (questionnaire_id) values (1);
insert into questionnaire_log (questionnaire_id) values (1);
insert into questionnaire_log (questionnaire_id) values (2);

insert into exam (name, questionnaire_log_id) values ('Edvinas', 1);
insert into exam (name, questionnaire_log_id) values ('E', 3);
insert into exam (name, questionnaire_log_id) values ('G', 2);

insert into answer (questionnaire_log_id, is_right, chosen_letter) values (1, true, 'a');
insert into answer (questionnaire_log_id, is_right, chosen_letter) values (2, false, 'c');
insert into answer (questionnaire_log_id, is_right, chosen_letter) values (3, true, 'b');