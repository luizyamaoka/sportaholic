CREATE ROLE sportaholic LOGIN password 'sportaholic';
CREATE DATABASE sportaholic ENCODING 'UTF8' OWNER sportaholic;
GRANT ALL PRIVILEGES ON DATABASE sportaholic TO sportaholic;