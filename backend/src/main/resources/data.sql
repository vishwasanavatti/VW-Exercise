Insert into COMPONENT (id, name, price, quantity) values (20001, 'component 1', 12.00, 15);
Insert into COMPONENT (id, name, price, quantity) values (20002, 'component 2', 25.20, 215);
Insert into COMPONENT (id, name, price, quantity) values (20003, 'component 3', 33.00, 35);
Insert into COMPONENT (id, name, price, quantity) values (20004, 'component 4', 45.00, 46);
Insert into COMPONENT (id, name, price, quantity) values (20005, 'component 5', 112.00, 64);

Insert into catalog (model, model_Name) values (1, 'model 1');
Insert into catalog (model, model_Name) values (2, 'model 2');
Insert into catalog (model, model_Name) values (3, 'model 3');
Insert into catalog (model, model_Name) values (4, 'model 4');
Insert into catalog (model, model_Name) values (5, 'model 5');

Insert into model_components (model_id, component_id) values (1, 20001);
Insert into model_components (model_id, component_id) values (1, 20002);
Insert into model_components (model_id, component_id) values (1, 20003);
Insert into model_components (model_id, component_id) values (2, 20002);
Insert into model_components (model_id, component_id) values (2, 20004);
Insert into model_components (model_id, component_id) values (3, 20001);
Insert into model_components (model_id, component_id) values (3, 20002);
Insert into model_components (model_id, component_id) values (3, 20003);
Insert into model_components (model_id, component_id) values (3, 20004);
Insert into model_components (model_id, component_id) values (3, 20005);
Insert into model_components (model_id, component_id) values (4, 20001);
Insert into model_components (model_id, component_id) values (4, 20002);
Insert into model_components (model_id, component_id) values (5, 20003);