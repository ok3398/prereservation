
INSERT INTO preservation_join (user_id, preserv_id, seq, reg_time) VALUES (1111,2222,max(seq)+1,'2018-08-05 00:00:00.000');

select preserv_id, count(preserv_id) as countn from preservation_join where preserv_id = 2222 group by preserv_id
