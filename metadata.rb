maintainer          "Vincent Demeester"
maintainer_email    "vincent@demeester.fr"
license             "Apache 2.0"
description         "Install mysql ruby gem"
long_description    IO.read(File.join(File.dirname(__FILE__), 'README.md'))
version             "0.1.0"

%w{ ubuntu debian }.each do |os|
    supports os
end
