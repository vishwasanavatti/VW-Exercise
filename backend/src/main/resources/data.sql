Insert into COMPONENT (id, name, price, quantity) values (20001, 'component 1', 12.00, 15);
Insert into COMPONENT (id, name, price, quantity) values (20002, 'component 2', 25.20, 215);
Insert into COMPONENT (id, name, price, quantity) values (20003, 'component 3', 33.00, 35);
Insert into COMPONENT (id, name, price, quantity) values (20004, 'component 4', 45.00, 46);
Insert into COMPONENT (id, name, price, quantity) values (20005, 'component 5', 112.00, 64);

Insert into Model_Catalog (model, model_Name) values (10001, 'model 1');
Insert into Model_Catalog (model, model_Name) values (10002, 'model 2');
Insert into Model_Catalog (model, model_Name) values (10003, 'model 3');
Insert into Model_Catalog (model, model_Name) values (10004, 'model 4');
Insert into Model_Catalog (model, model_Name) values (10005, 'model 5');

Insert into model_components (model_id, component_id) values (10001, 20001);
Insert into model_components (model_id, component_id) values (10001, 20002);
Insert into model_components (model_id, component_id) values (10001, 20003);
Insert into model_components (model_id, component_id) values (10002, 20002);
Insert into model_components (model_id, component_id) values (10002, 20004);
Insert into model_components (model_id, component_id) values (10003, 20001);
Insert into model_components (model_id, component_id) values (10003, 20002);
Insert into model_components (model_id, component_id) values (10003, 20003);
Insert into model_components (model_id, component_id) values (10003, 20004);
Insert into model_components (model_id, component_id) values (10003, 20005);
Insert into model_components (model_id, component_id) values (10004, 20001);
Insert into model_components (model_id, component_id) values (10004, 20002);
Insert into model_components (model_id, component_id) values (10005, 20003);