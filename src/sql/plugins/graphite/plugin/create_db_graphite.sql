

--
-- Structure for table graphite_graph
--

DROP TABLE IF EXISTS graphite_graph;
CREATE TABLE graphite_graph (		
id_graph int(11) NOT NULL default '0',
graph_title varchar(50) NOT NULL default '',
graph_url varchar(500) NOT NULL default '',
graph_order int(11) NOT NULL default '0',
PRIMARY KEY (id_graph)
);
