```sql
select id from customers where first_name = :#firstname and last_name = :#lastname
```

```sql
INSERT INTO addresses(customer_id,street,city,state,zip,type) VALUES(:#customerId,:#street,:#city,:#state,:#zip,:#type)
```