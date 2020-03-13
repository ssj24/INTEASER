const path = require('path');

module.exports = {
    configureWebpack: {
        resolve: {
            alias: {
                '@': path.join(__dirname, 'src/')
            }
        }
    }
}

// module.exports = {
//     devServer: {
//       https: true,
//       hotOnly: false,
//       public: 'https://localhost:8080/'
//     },
//   }