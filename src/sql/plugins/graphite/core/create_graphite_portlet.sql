  
--
-- Structure for table graphite_portlet
--
DROP TABLE IF EXISTS graphite_portlet;
CREATE TABLE graphite_portlet (
  id_portlet int default '0' NOT NULL,
  graphite_feed_id varchar(100) default NULL,
  PRIMARY KEY  (id_portlet)
);
