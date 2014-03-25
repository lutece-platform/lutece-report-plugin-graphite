INSERT INTO `graphite_category` VALUES
(1, 'Caches', 'none', 'all', 1, 1),
(2, 'Pages', 'none', 'all', 1, 1),
(3, 'Connexions', 'none', 'all', 1, 1);
INSERT INTO `graphite_graph` VALUES 
(1,'Taille des caches Lutece','http://dev.lutece.paris.fr/render/?yMin=0&width=588&height=310&from=-60min&lineMode=connected&target=servers.sitedemo.lutece.cache.pageService.memorySize&target=servers.sitedemo.lutece.cache.documentResourceServlet.memorySize&target=servers.sitedemo.lutece.cache.portletService.memorySize', 1, 1, 'Ce graphique permet de savoir quelle est la taille des caches des services Lutèce'),
(2,'Nombre d\'objets dans les caches', 'http://dev.lutece.paris.fr/render/?yMin=0&width=588&height=310&from=-60min&lineMode=connected&target=servers.sitedemo.lutece.cache.datastore.Size&target=servers.sitedemo.lutece.cache.pageService.Size&target=servers.sitedemo.lutece.cache.pageCachingFilter.Size&target=servers.sitedemo.lutece.cache.portletService.Size&target=servers.sitedemo.lutece.cache.staticFilesCachingFilter.Size&target=servers.sitedemo.lutece.cache.xmlTransformerService.Size', 2, 1, 'Ce graphique permet de savoir combien il y a d\'objets stockés temporairement dans les caches'),
(3,'Nombre de pages du site', 'http://dev.lutece.paris.fr/render/?yMin=0&width=588&height=310&from=-45min&lineMode=connected&target=servers.sitedemo.lutece.portal.pageCount',1, 2, 'Ce graphique permet de savoir combien il y a de pages sur le site demo'),
(4,'Nombre d\'utilisateurs connectés (1 heure)', 'http://dev.lutece.paris.fr/render/?yMin=0&width=588&height=310&from=-60min&lineMode=connected&target=servers.sitedemo.lutece.technical.limitconnectedusers.nbSessions', 1, 3, 'Ce graphique permet de savoir combien il y a d\'utilisateurs connectés depuis la dernière heure'),
(5,'Nombre d\'utilisateurs connectés (24 heures)', 'http://dev.lutece.paris.fr/render/?yMin=0&width=588&height=310&from=-24h&lineMode=connected&target=servers.sitedemo.lutece.technical.limitconnectedusers.nbSessions', 2, 3, 'Ce graphique permet de savoir combien il y a d\'utilisateurs connectés depuis les 24 dernières heures'),
(6,'Nombre d\'utilisateurs connectés (7 jours)', 'http://dev.lutece.paris.fr/render/?yMin=0&width=588&height=310&from=-7d&lineMode=connected&target=servers.sitedemo.lutece.technical.limitconnectedusers.nbSessions', 3, 3, 'Ce graphique permet de savoir combien il y a d\'utilisateurs connectés depuis les 7 derniers jours');