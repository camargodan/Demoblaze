function fn() {
  var config = {
    baseUrl: 'https://api.demoblaze.com'
  };

  karate.configure('connectTimeout', 10000);
  karate.configure('readTimeout', 10000);

  return config;
}
