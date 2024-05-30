INSERT INTO tool_data (code, type, brand) VALUES ('CHNS', 'Chainsaw', 'Stihl');
INSERT INTO tool_data (code, type, brand) VALUES ('LADW', 'Ladder', 'Werner');
INSERT INTO tool_data (code, type, brand) VALUES ('JAKD', 'Jackhammer', 'DeWalt');
INSERT INTO tool_data (code, type, brand) VALUES ('JAKR', 'Jackhammer', 'Ridgid');

INSERT INTO tool_charges (type, daily_charge, weekend_charge, holiday_charge) VALUES ('Ladder', 199, true, false);
INSERT INTO tool_charges (type, daily_charge, weekend_charge, holiday_charge) VALUES ('Chainsaw', 149, false, true);
INSERT INTO tool_charges (type, daily_charge, weekend_charge, holiday_charge) VALUES ('Jackhammer', 299, false, false);