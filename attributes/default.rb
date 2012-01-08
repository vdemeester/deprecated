#
# Cookbook Name:: redmine
# Attributes:: default
#
# Author:: Vincent Demeester <vincent@demeester.fr>
#
# Copyright 2011, Vincent Demeester
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
# http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
require 'openssl'

pw = String.new

while pw.length < 20
      pw << OpenSSL::Random.random_bytes(1).gsub(/\W/, '')
end
#
default[:rails][:version] = '2.3.14'

default[:redmine][:version] = '1.3.0'
default[:redmine][:dl_id] = '75597'
default[:redmine][:checksum] = '4aa3534ae6a06bc3732b1c8b6eee7c60'
default[:redmine][:mirror] = 'http://rubyforge.org/frs/download.php'

default[:redmine][:lang] = 'en'

default[:redmine][:db][:type] = "sqlite"
default[:redmine][:db][:user] = "redmine"
default[:redmine][:db][:password] = pw
default[:redmine][:db][:hostname] = "localhost"
