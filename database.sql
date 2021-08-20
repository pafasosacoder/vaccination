INSERT INTO public.role(
	id, name)
	VALUES (1, 'ADMIN'), (2,'USER');
	
INSERT INTO public.inventory(
	id, active, brand, quantity)
	VALUES 
	(1, true, 'Sputnik', 10),
	(2, true, 'AstraZeneca', 25),
	(3, true, 'Pfizer', 32),
	(4, true, 'Moderna', 12),
	(5, true, 'Sinovac', 20);


INSERT INTO public.http_mapping(
	id, uri)
	VALUES
	    ('655db2e5-fff7-4993-b827-6685e7838e45', '/api/inventory/list'),
	    ('0da43dce-48bd-442c-8048-74cce7e94c31', '/api/inventory/save');

INSERT INTO public.http_mapping_role(
	http_mapping_id, role_id)
	VALUES
	    ('655db2e5-fff7-4993-b827-6685e7838e45', 1),
	    ('0da43dce-48bd-442c-8048-74cce7e94c31', 1);