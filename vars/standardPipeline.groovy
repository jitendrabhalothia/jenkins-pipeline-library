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
            
             parameters {
                choice(
                    // choices are a string of newline separated values
                    choices: 'rabbitmq\nredis\ntusker\npheme\ndroms\nopenadr2b\nceep\nndianoga\ncascade\nrtcc\nfam-backend\nall',
                    description: '',
                    name: 'REQUESTED_ACTION')
            }

            stages {
                stage ('dev05-rabbitmq') {
                    when {
                        // Only say hello if a "rabbitmq" is requested
                        expression { params.REQUESTED_ACTION == 'rabbitmq'  || params.REQUESTED_ACTION == 'all'}
                    }
                    steps {
                        echo 'Depoying rabbitmq for dev05-rabbitmq'
                        echo params.REQUESTED_ACTION
                
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



            }
        }
    }
