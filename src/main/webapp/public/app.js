
var Doctor = React.createClass({

    getInitialState : function() {
        return {display: true};
    },

    handleDelete() {
        var self = this;
        $.ajax({
            url:self.props.doctor._links.self.href,
            type : 'DELETE',
            success : function(result) {
                self.setState({display : false});
            },
            error : function(xhr , ajaxOptions, thrownError) {
                toastr.error(xhr , responseJSON.message);
            }
        });
    },

    render : function() {
        if(this.state.display == false) return null;
        else return(
            <tr>
                <td>{this.props.doctor.doc_id}</td>
                <td>{this.props.doctor.firstName}</td>
                <td>{this.props.doctor.lastName}</td>
                <td>{this.props.doctor.description}</td>
                <td>{this.props.doctor.city}</td>
                <td>
                    <button className="btn btn-info" onClick={this.handleDelete}>Delete</button>
                </td>
            </tr>
        );
    }
});
var DoctorTable = React.createClass({
    render : function() {
        var rows = [];
        this.props.doctors.forEach(function(doctor) {
            rows.push(
                <Doctor doctor={doctor} key={doctor.doc_id} />);
        });
        return (
            <div className="container">
                <table className="table table-striped">
                    <thead>
                        <tr>
                            <th>Unique_ID</th>
                            <th>First Name</th>
                            <th>Last Name</th>
                            <th>Specialization</th>
                            <th>City</th>
                            <th>DELETE (X) </th>
                        </tr>
                    </thead>
                    <tbody>{rows}</tbody>
                </table>
            </div>
        );
    }
});

var App = React.createClass({
    loadDoctorsFromServer : function() {
        var self = this;
        $.ajax({
            url: "http://localhost:8200/doctors",
        }).then( function(data) {
            self.setState({
                doctors : data._embedded.doctors
            });
        });
    },
    getInitialState : function() {
        return {
            doctors : []
        };
    },
    componentDidMount: function() {
        this.loadDoctorsFromServer();
    },
    render() {
        return (
            <DoctorTable doctors= {this.state.doctors} />
        );
    }
});
/*var EMPLOYEES = [
  {name: 'Joe Biden', age: 45, years: 5},
  {name: 'President Obama', age: 54, years: 8},
  {name: 'Crystal Mac', age: 34, years: 12},
  {name: 'James Henry', age: 33, years: 2}
];*/
ReactDOM.render( 
    <App /> , document.getElementById('root')
);