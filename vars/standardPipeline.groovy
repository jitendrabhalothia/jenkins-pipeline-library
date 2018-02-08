def call(body) {

        def config = [:]
        def PLAYBOOK_PATH = "~/workspace/JenkinsFile_GIT_Repo/ansible/playbooks"
        body.resolveStrategy = Closure.DELEGATE_FIRST
        body.delegate = config
        body()

        pipeline {
            agent {
                label 'master'
            }
             

            stages {
                stage ('dev05-rabbitmq') {
                    script {
                        env.REQUEST_ACTION_RMQ = input message: 'User input required', parameters: [choice(name: 'REQUESTED_ACTION', choices: '\rabbitmq\nredis\ntusker\npheme\ndroms\nopenadr2b\nceep\nndianoga\ncascade\nrtcc\nfam-backend\nall', description: 'What is the release scope?')]

                         
                    }
                    when {
                        // Only say hello if a "rabbitmq" is requested
                        expression { env.REQUEST_ACTION_RMQ == 'rabbitmq'  || env.REQUEST_ACTION_RMQ == 'all'}
                    }
                    steps {
                        echo 'Depoying rabbitmq for dev05'
                
                        sh "cd ${PLAYBOOK_PATH}/${config.folderName} && cp ~/ansible.cfg ansible.cfg && sudo ansible-playbook -i ${PLAYBOOK_PATH}/${config.folderName}/inventory/${config.envName} ${PLAYBOOK_PATH}/${config.folderName}/rabbitmq.yml --tags update --vault-password-file  ~/.agv"

                    }
                }
                stage ('dev05-redis') {
                    when {
                        // Only say hello if a "redis is requested
                        expression { params.REQUESTED_ACTION == 'redis'  || params.REQUESTED_ACTION == 'all'}
                    }
                    steps {
                        echo 'Depoying rabbitmq for dev05'
                
                        sh "cd ${PLAYBOOK_PATH}/${config.folderName} && cp ~/ansible.cfg ansible.cfg && sudo ansible-playbook -i ${PLAYBOOK_PATH}/${config.folderName}/inventory/${config.envName} ${PLAYBOOK_PATH}/${config.folderName}/redis.yml --tags update --vault-password-file  ~/.agv"

                    }
                }
                stage ('dev05-droms') {
                    script {
                        env.REQUEST_ACTION_DRM = input message: 'User input required', parameters: [choice(name: 'REQUESTED_ACTION', choices: '\rabbitmq\nredis\ntusker\npheme\ndroms\nopenadr2b\nceep\nndianoga\ncascade\nrtcc\nfam-backend\nall', description: 'What is the release scope?')]

                         
                    }
                    when {
                        // Only say hello if a "droms" is requested
                        expression { env.REQUEST_ACTION_DRM == 'droms'  || env.REQUEST_ACTION_DRM == 'all'}
                    }
                    steps {
                        echo 'Depoying rabbitmq for dev05'
                
                        sh "cd ${PLAYBOOK_PATH}/${config.folderName} && cp ~/ansible.cfg ansible.cfg && sudo ansible-playbook -i ${PLAYBOOK_PATH}/${config.folderName}/inventory/${config.envName} ${PLAYBOOK_PATH}/${config.folderName}/droms.yml --tags update --vault-password-file  ~/.agv"

                    }
                }


            }
        }
    }
