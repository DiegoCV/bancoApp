function fn() {
  const uuid = java.util.UUID.randomUUID();
  const config = {
    headers: {
      'Message-Id': uuid,
      'Another-Header': 'value'
    },
    oasUrl: '',
    urlBase: 'http://localhost:8080/api'
  };

  karate.configure('connectTimeout', 2000);
  karate.configure('readTimeout', 2000);
  karate.configure('ssl', false);
  return config;
}